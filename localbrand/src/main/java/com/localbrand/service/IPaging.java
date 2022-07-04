package com.localbrand.service;

import java.util.List;

public interface IPaging {
	 public List<Integer> getListNumberBox(Integer page, Integer totalPage);
	 public Integer getPage(String op, Integer totalPage, Integer curentPage, Integer gotoPage);

}
