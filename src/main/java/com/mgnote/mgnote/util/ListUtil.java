package com.mgnote.mgnote.util;


import com.mgnote.mgnote.model.dto.ListParam;
import com.mgnote.mgnote.model.dto.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListUtil {


    public static Pageable getPageableByListParam(ListParam listParam) {
        if(new Integer(0).equals(listParam.getPageSize())){
            return Pageable.unpaged();
        }
        if(!Optional.ofNullable(listParam.getCurrentPage()).isPresent()
                || !Optional.ofNullable(listParam.getPageSize()).isPresent())
            throw new IllegalArgumentException();
        if(!Optional.ofNullable(listParam.getOrderBy()).isPresent())
            return PageRequest.of(listParam.getCurrentPage() - 1, listParam.getPageSize());
        List<Sort.Order> orderList = new ArrayList<>();
        for (Order order : listParam.getOrderBy()){
            orderList.add(new Sort.Order(order.getDirection(),order.getProperty()));
        }
        return PageRequest.of(listParam.getCurrentPage() - 1, listParam.getPageSize(),
                Sort.by(orderList));
    }

    public static <T> ListParam getListParamByPage(Page<T> page) {
        return new ListParam(page.getNumber() + 1, page.getNumberOfElements(),
                null, page.getTotalElements(), page.getTotalPages());
    }
}
