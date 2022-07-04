package com.localbrand.service.implement;

import java.util.ArrayList;
import java.util.List;

import com.localbrand.service.IPaging;

public class PagingService implements IPaging {

	@Override
	public List<Integer> getListNumberBox(Integer page, Integer totalPage) {
		List<Integer> listNumberBox = new ArrayList<>();
    	if(page == 1 || totalPage <= 3) {
       	 listNumberBox.add(1);
            listNumberBox.add(2);
            listNumberBox.add(3);

        }
    	else if(page == totalPage) {
       	 listNumberBox.add(page-2);
            listNumberBox.add(page-1);
            listNumberBox.add(page);
        }
    	else if(page > 1 && page < totalPage) {
       	 listNumberBox.add(page-1);
            listNumberBox.add(page);
            listNumberBox.add(page+1);
        }
    	return listNumberBox;
	}

	@Override
	public Integer getPage(String op, Integer totalPage, Integer curentPage, Integer gotoPage) {
		Integer page = curentPage;
        if (op == null) {
            op = "FirstPage";
        }
        switch (op) {
            case "FirstPage":
                page = 1;
                break;
            case "PreviousPage":
                if (page > 1) {
                    page--;
                }
                break;
            case "NextPage":
                if (page < totalPage) {
                    page++;
                }
                break;
            case "LastPage":
                page = totalPage;
                break;
            case "GotoPage":
                page = gotoPage;
                break;
        }
        return page;
	}

}
