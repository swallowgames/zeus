/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.f6car.base.config;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import com.f6car.base.common.Result;
import com.f6car.base.core.ExcelExport;
import com.f6car.base.core.ExcelExportParam;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;

import static com.f6car.base.core.F6Static.getExcelExportParam;

/**
 * @author qixiaobo
 */
public class ExcelHttpMessageConverter extends AbstractHttpMessageConverter<Object>//
        implements GenericHttpMessageConverter<Object> {
    public static final MediaType EXCEL_MEDIA_TYPE = new MediaType("application", "vnd.ms-excel");

    public ExcelHttpMessageConverter() {
        super(EXCEL_MEDIA_TYPE);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        Collection data = getActualData((Result) o);
        ExcelExportParam excelExportParam = getExcelExportParam();
        switch (excelExportParam.getExcelExport()) {
            case NormalExcel:
                Workbook workbook = ExcelExportUtil.exportExcel(
                        excelExportParam.getExportParams(),
                        (Class<?>) excelExportParam.getClazz(),
                        (Collection<?>) data);
                workbook.write(outputMessage.getBody());
                break;
            case BigExcel:

            case MapExcel:
                //TODO
            case MapExcelGraph:

            case PDFTemplate:

            case TemplateExcel:

            case TemplateWord:
            default:
                throw new RuntimeException();
        }
        if (excelExportParam.getFileName() != null) {
            String codedFileName = URLEncoder.encode(excelExportParam.getFileName(), "UTF8");
            headers.add("content-disposition", "attachment;filename=" + codedFileName);
        }

    }

    private Collection getActualData(Result r) {
        if (r != null && r.getData() != null) {
            Object data = r.getData();
            if (data instanceof PageInfo) {
                return ((PageInfo) data).getList();
            } else if (!(data instanceof Collection)) {
                data = Lists.newArrayList(data);
            } else {
                return (Collection) data;
            }
        }
        return Collections.emptyList();


    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        //不支持excel
        return false;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return super.canWrite(mediaType) && clazz == Result.class && support();
    }


    private boolean support() {
        ExcelExportParam param = getExcelExportParam();
        if (param == null || param.getExcelExport() == null || param.getExportParams() == null) {
            return false;
        }
        if (param.getExcelExport() == ExcelExport.NormalExcel) {
            return true;
        } else {
            logger.warn(param.getExcelExport() + " not supprot now!");
            return false;
        }

    }

    @Override
    public void write(Object o, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.write(o, contentType, outputMessage);
    }
}
