package com.wxsl.rosalind.framework.ioc.api;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PropertyEditorRegistrarDemo implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor customDateEditor = new CustomDateEditor(simpleDateFormat, true);
        registry.registerCustomEditor(Date.class, customDateEditor);
    }
}
