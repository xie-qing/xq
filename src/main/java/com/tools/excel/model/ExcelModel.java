package com.tools.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ExampleProperty;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/8/7 17:29
 */
public class ExcelModel extends BaseRowModel {

    @ExcelProperty(value = "名称" , index = 0)
    String name;

}
