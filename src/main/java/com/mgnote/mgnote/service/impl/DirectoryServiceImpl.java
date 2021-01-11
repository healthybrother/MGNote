package com.mgnote.mgnote.service.impl;

import com.google.common.base.Preconditions;
import com.mgnote.mgnote.exception.EntityNotExistException;
import com.mgnote.mgnote.exception.PermissionException;
import com.mgnote.mgnote.model.*;
import com.mgnote.mgnote.model.dto.BriefNote;
import com.mgnote.mgnote.repository.DirectoryRepository;
import com.mgnote.mgnote.repository.NoteRepository;
import com.mgnote.mgnote.repository.UserRepository;
import com.mgnote.mgnote.service.DirectoryService;
import com.mgnote.mgnote.util.EntityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DirectoryServiceImpl implements DirectoryService {

    private final Logger log = LoggerFactory.getLogger(DirectoryServiceImpl.class);
    private final NoteRepository noteRepository;
    private final DirectoryRepository directoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public DirectoryServiceImpl(NoteRepository noteRepository, UserRepository userRepository, DirectoryRepository directoryRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.directoryRepository = directoryRepository;
    }

    @Override
    public BriefNote addNote(String path, Note note) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(note, "note为空");

        if(path.charAt(0) != '/')
            throw new PermissionException("路径非法");

        List<String> idList = new ArrayList<>(Arrays.asList(path.split("/")));
        idList.remove(0);
        if (idList.size() <= 1)
            throw new PermissionException("路径非法");
        String noteBookId = idList.remove(idList.size() - 1);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);

        NoteBook targetNoteBook;
        List<Directory> directoryList = new ArrayList<>();

        Note after = EntityUtil.copyProperties(Note.getNewNote(), note, true);
        after.setId(UUID.randomUUID().toString());
        after.setUserId(rootDirectory.getUserId());

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            targetNoteBook = directoryList.get(directoryList.size() - 1).getNoteBookList().stream().filter(
                    noteBook -> noteBook.getId().equals(noteBookId)
            ).findAny().orElse(null);
        } else {
            targetNoteBook = rootDirectory.getNoteBookList().stream().filter(
                    noteBook -> noteBook.getId().equals(noteBookId)
            ).findAny().orElse(null);
        }
        if (null == targetNoteBook)
            throw new EntityNotExistException("找不到路径中笔记本");

        after.setNotebook(targetNoteBook.getId());
        NoteBook temp1 = EntityUtil.copyProperties(targetNoteBook, new NoteBook(), true);
        List<BriefNote> temp2 = null == temp1.getNoteList() ? new ArrayList<>() : temp1.getNoteList();
        temp2.add(new BriefNote(after.getId(), after.getName()));
        temp1.setNoteList(temp2);

        if (directoryList.isEmpty()) {
            List<NoteBook> temp3 = rootDirectory.getNoteBookList();
            temp3.remove(targetNoteBook);
            temp3.add(temp1);
            rootDirectory.setNoteBookList(temp3);
        } else {
            Directory temp4 = EntityUtil.copyProperties(directoryList.get(directoryList.size() - 1), new Directory(), true);
            List<NoteBook> temp5 = temp4.getNoteBookList();
            temp5.remove(targetNoteBook);
            temp5.add(temp1);
            temp4.setNoteBookList(temp5);
            saveDirectoryOfPath(rootDirectory, directoryList, temp4);
        }

        noteRepository.save(after);
        directoryRepository.save(rootDirectory);

        return new BriefNote(after.getId(), after.getName());
    }

    @Override
    public String addNoteBook(String path, NoteBook noteBook) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(noteBook, "noteBook为空");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;

        noteBook.setId(UUID.randomUUID().toString());
        noteBook.setNoteList(new ArrayList<>());

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<NoteBook> temp2 = temp1.getNoteBookList();
            temp2.add(noteBook);
            temp1.setNoteBookList(temp2);
            saveDirectoryOfPath(rootDirectory, directoryList, temp1);
        } else {
            List<NoteBook> temp3 = rootDirectory.getNoteBookList();
            temp3.add(noteBook);
            rootDirectory.setNoteBookList(temp3);
        }

        directoryRepository.save(rootDirectory);

        return noteBook.getId();
    }

    @Override
    public String addDirectory(String path, Directory directory) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(directory, "directory为空");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;

        directory.setId(UUID.randomUUID().toString());
        directory.setDirectoryList(new ArrayList<>());
        directory.setNoteBookList(new ArrayList<>());

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<Directory> temp2 = temp1.getDirectoryList();
            temp2.add(directory);
            temp1.setDirectoryList(temp2);
            saveDirectoryOfPath(rootDirectory, directoryList, temp1);
        } else {
            List<Directory> temp3 = rootDirectory.getDirectoryList();
            temp3.add(directory);
            rootDirectory.setDirectoryList(temp3);
        }

        directoryRepository.save(rootDirectory);

        return directory.getId();
    }

    @Override
    public void updateNoteBook(String path, String id, NoteBook noteBook) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(id, "noteBookId为空");
        Preconditions.checkNotNull(noteBook, "noteBook为空");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<NoteBook> temp2 = temp1.getNoteBookList();
            for (NoteBook n : temp2) {
                if (n.getId().equals(id)) {
                    noteBook.setId(n.getId());
                    noteBook.setNoteList(n.getNoteList());
                    temp2.remove(n);
                    break;
                }
            }
            temp2.add(noteBook);
            temp1.setNoteBookList(temp2);
            saveDirectoryOfPath(rootDirectory, directoryList, temp1);
        } else {
            List<NoteBook> temp3 = rootDirectory.getNoteBookList();
            for (NoteBook n : temp3) {
                if (n.getId().equals(id)) {
                    noteBook.setId(n.getId());
                    noteBook.setNoteList(n.getNoteList());
                    temp3.remove(n);
                    break;
                }
            }
            temp3.add(noteBook);
            rootDirectory.setNoteBookList(temp3);
        }

        directoryRepository.save(rootDirectory);
    }

    @Override
    public void updateDirectory(String path, String id, Directory directory) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(id, "noteBookId为空");
        Preconditions.checkNotNull(directory, "directory为空");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<Directory> temp2 = temp1.getDirectoryList();
            for (Directory n : temp2) {
                if (n.getId().equals(id)) {
                    directory.setId(n.getId());
                    directory.setDirectoryList(n.getDirectoryList());
                    directory.setNoteBookList(n.getNoteBookList());
                    temp2.remove(n);
                    break;
                }
            }
            temp2.add(directory);
            temp1.setDirectoryList(temp2);
            saveDirectoryOfPath(rootDirectory, directoryList, temp1);
        } else {
            List<Directory> temp3 = rootDirectory.getDirectoryList();
            for (Directory n : temp3) {
                if (n.getId().equals(id)) {
                    directory.setId(n.getId());
                    directory.setDirectoryList(n.getDirectoryList());
                    directory.setNoteBookList(n.getNoteBookList());
                    temp3.remove(n);
                    break;
                }
            }
            temp3.add(directory);
            rootDirectory.setDirectoryList(temp3);
        }

        directoryRepository.save(rootDirectory);
    }

    @Override
    public void updateNoteInfo(String path, BriefNote briefNote) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(briefNote, "briefNote为空");

        List<String> idList = new ArrayList<>(Arrays.asList(path.split("/")));
        idList.remove(0);
        if (idList.size() <= 1)
            throw new PermissionException("路径非法");
        String noteBookId = idList.remove(idList.size() - 1);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);

        NoteBook targetNoteBook;
        List<Directory> directoryList = new ArrayList<>();

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            targetNoteBook = directoryList.get(directoryList.size() - 1).getNoteBookList().stream().filter(
                    noteBook -> noteBook.getId().equals(noteBookId)
            ).findAny().orElse(null);
        } else {
            targetNoteBook = rootDirectory.getNoteBookList().stream().filter(
                    noteBook -> noteBook.getId().equals(noteBookId)
            ).findAny().orElse(null);
        }
        if (null == targetNoteBook)
            throw new EntityNotExistException("找不到路径中笔记本");
        NoteBook temp1 = EntityUtil.copyProperties(targetNoteBook, new NoteBook(), true);
        List<BriefNote> temp2 = null == temp1.getNoteList() ? new ArrayList<>() : temp1.getNoteList();
        for (BriefNote b : temp2) {
            if (b.getId().equals(briefNote.getId())) {
                temp2.remove(b);
                break;
            }
        }
        temp2.add(briefNote);
        temp1.setNoteList(temp2);

        if (directoryList.isEmpty()) {
            List<NoteBook> temp3 = rootDirectory.getNoteBookList();
            temp3.remove(targetNoteBook);
            temp3.add(temp1);
            rootDirectory.setNoteBookList(temp3);
        } else {
            Directory temp4 = EntityUtil.copyProperties(directoryList.get(directoryList.size() - 1), new Directory(), true);
            List<NoteBook> temp5 = temp4.getNoteBookList();
            temp5.remove(targetNoteBook);
            temp5.add(temp1);
            temp4.setNoteBookList(temp5);
            saveDirectoryOfPath(rootDirectory, directoryList, temp4);
        }

        directoryRepository.save(rootDirectory);
    }

    @Override
    public Directory getDirectoryById(String path, String id) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(id, "id为null");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<Directory> temp2 = temp1.getDirectoryList();
            for (Directory d : temp2) {
                if (d.getId().equals(id))
                    return d;
            }
        } else {
            List<Directory> temp3 = rootDirectory.getDirectoryList();
            for (Directory d : temp3) {
                if (d.getId().equals(id))
                    return d;
            }
        }
        throw new EntityNotExistException("找不到文件夹");
    }

    @Override
    public NoteBook getNoteBookById(String path, String id) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(id, "id为null");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<NoteBook> temp2 = temp1.getNoteBookList();
            for (NoteBook d : temp2) {
                if (d.getId().equals(id))
                    return d;
            }
        } else {
            List<NoteBook> temp3 = rootDirectory.getNoteBookList();
            for (NoteBook d : temp3) {
                if (d.getId().equals(id))
                    return d;
            }
        }
        throw new EntityNotExistException("找不到文件夹");
    }

    @Override
    public void deleteNoteBook(String path, String id) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(id, "noteBookId为空");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;
        List<String> noteIdList = new ArrayList<>();
        NoteBook delete = null;

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<NoteBook> temp2 = temp1.getNoteBookList();
            for (NoteBook n : temp2) {
                if (n.getId().equals(id)) {
                    delete = n;
                    noteIdList = n.getNoteList().stream().map(BriefNote::getId).collect(Collectors.toList());
                    temp2.remove(n);
                    break;
                }
            }
            temp1.setNoteBookList(temp2);
            saveDirectoryOfPath(rootDirectory, directoryList, temp1);
        } else {
            List<NoteBook> temp3 = rootDirectory.getNoteBookList();
            for (NoteBook n : temp3) {
                if (n.getId().equals(id)) {
                    delete = n;
                    noteIdList = n.getNoteList().stream().map(BriefNote::getId).collect(Collectors.toList());
                    temp3.remove(n);
                    break;
                }
            }
            rootDirectory.setNoteBookList(temp3);
        }
        if (null == delete)
            throw new EntityNotExistException("笔记本不存在");

        noteRepository.deleteAllByIdIn(noteIdList);
        directoryRepository.save(rootDirectory);
    }

    @Override
    public void deleteDirectory(String path, String id) {
        Preconditions.checkNotNull(path, "无路径信息");
        Preconditions.checkNotNull(id, "noteBookId为空");

        List<String> idList = getIdListByPath(path);

        String rootDirectoryId = idList.remove(0);
        RootDirectory rootDirectory = getRootDirectoryById(rootDirectoryId);
        List<Directory> directoryList;
        List<String> noteIdList;
        Directory delete = null;

        if (idList.size() >= 1) {
            directoryList = getDirectoryOfPath(rootDirectory, idList);
            Directory temp1 = directoryList.get(directoryList.size() - 1);
            List<Directory> temp2 = temp1.getDirectoryList();
            for (Directory n : temp2) {
                if (n.getId().equals(id)) {
                    delete = n;
                    temp2.remove(n);
                    break;
                }
            }
            temp1.setDirectoryList(temp2);
            saveDirectoryOfPath(rootDirectory, directoryList, temp1);
        } else {
            List<Directory> temp3 = rootDirectory.getDirectoryList();
            for (Directory n : temp3) {
                if (n.getId().equals(id)) {
                    delete = n;
                    temp3.remove(n);
                    break;
                }
            }
            rootDirectory.setDirectoryList(temp3);
        }

        if (null == delete) {
            throw new EntityNotExistException("文件夹不存在");
        }
        noteIdList = getNoteIdList(delete);

        noteRepository.deleteAllByIdIn(noteIdList);
        directoryRepository.save(rootDirectory);
    }

    @Override
    public RootDirectory getRootDirectoryByUserId(String userId) {
        Preconditions.checkNotNull(userId, "userId is null");
        Optional<User> optUser = userRepository.findById(userId);
        if (!optUser.isPresent())
            throw new EntityNotExistException("用户不存在");
        String id = optUser.get().getDirectoryId();
        Optional<RootDirectory> opt = directoryRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        throw new EntityNotExistException("根文件夹不存在");
    }

    @Override
    public RootDirectory getRootDirectoryById(String id) {
        Preconditions.checkNotNull(id, "id is null");
        Optional<RootDirectory> opt = directoryRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        throw new EntityNotExistException("根文件夹不存在");
    }

    public List<Directory> getDirectoryOfPath(RootDirectory rootDirectory, List<String> idList) {
        List<Directory> directoryList = new ArrayList<>();
        Directory temp = rootDirectory.getDirectoryList().stream().filter(
                directory -> directory.getId().equals(idList.get(0))
        ).findAny().orElse(null);
        if (null == temp)
            throw new EntityNotExistException("找不到路径中文件夹");
        directoryList.add(temp);
        for (int i = 1; i < idList.size() - 1; i++) {
            int tempI = i;
            temp = temp.getDirectoryList().stream().filter(
                    directory -> directory.getId().equals(idList.get(tempI))
            ).findAny().orElse(null);
            if (null == temp)
                throw new EntityNotExistException("找不到路径中文件夹");
            directoryList.add(temp);
        }
        return directoryList;
    }

    public void saveDirectoryOfPath(RootDirectory rootDirectory, List<Directory> directoryList, Directory newVersionOfLast) {
        Directory fDirectory;
        Directory oldDirectory;
        for (int i = directoryList.size() - 2; i >= 0; i--) {
            fDirectory = directoryList.get(i);
            oldDirectory = directoryList.get(i + 1);
            String oldId = oldDirectory.getId();
            List<Directory> temp2 = fDirectory.getDirectoryList();
            for (Directory m : temp2) {
                if (m.getId().equals(oldId)) {
                    temp2.remove(m);
                    break;
                }
            }
            temp2.add(newVersionOfLast);
            fDirectory.setDirectoryList(temp2);
            newVersionOfLast = fDirectory;
        }
        List<Directory> temp1 = rootDirectory.getDirectoryList();
        for (Directory m : temp1) {
            if (m.getId().equals(directoryList.get(0).getId())) {
                temp1.remove(m);
                break;
            }
        }
        temp1.add(newVersionOfLast);
        rootDirectory.setDirectoryList(temp1);
    }

    List<String> getIdListByPath(String path) {
        if(path.charAt(0) != '/')
            throw new PermissionException("路径非法");
        List<String> idList = new ArrayList<>(Arrays.asList(path.split("/")));
        idList.remove(0);
        if (idList.size() == 0)
            throw new PermissionException("路径非法");
        return idList;
    }

    List<String> getNoteIdList(Directory directory) {
        List<String> noteIdList = new ArrayList<>();
        for (NoteBook n : directory.getNoteBookList()) {
            noteIdList.addAll(n.getNoteList().stream().map(BriefNote::getId).collect(Collectors.toList()));
        }
        for (Directory d : directory.getDirectoryList()) {
            noteIdList.addAll(getNoteIdList(d));
        }
        return noteIdList;
    }
}
