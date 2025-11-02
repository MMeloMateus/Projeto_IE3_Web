package com.controleDeAcesso.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;


import java.util.List;

public class PageUtils {

    public static <T> Page<T> toPage(List<T> list, Pageable pageable){
        int total = list.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start+pageable.getPageSize()),total);
        List<T> subList = list.subList(start,end);
        return new PageImpl<>(subList,pageable,total);
    }

}
