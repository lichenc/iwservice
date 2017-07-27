/**   
 * Copyright (c) 2004-2014 i-Sprint Technologies, Inc.
 * address: 
 * All rights reserved. 
 * 
 * This software is the confidential and proprietary information of 
 * i-Sprint Technologies, Inc. ("Confidential Information").  You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of the license agreement you entered into 
 * with i-Sprint. 
 *
 * @Title: DataGrid.java 
 * @author zhushaohong
 * @Package com.isprint.server.cds.basic.util 
 * @Description: TODO(simple description this file what to do.) 
 * @date 2014Âπ?9Êú?18Êó? ‰∏äÂçà9:35:07 
 * @version V1.0   
 */
package com.iws.util;

import java.util.ArrayList;
import java.util.List;

public class DataGrid<T> {

    private int total = 0;

    private List<T> rows = new ArrayList<T>();

    public DataGrid() {
    }

    public DataGrid(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
