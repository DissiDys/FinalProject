package com.example.model.entity.customTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            for (int page = 1; page <= (Integer) pageContext.getRequest().getAttribute("page"); page++) {
                out.print("<a class=\"pages\"href=\"/app/activitiesCategories?page=" + page + "\">" + page + "</a>");
            }
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
