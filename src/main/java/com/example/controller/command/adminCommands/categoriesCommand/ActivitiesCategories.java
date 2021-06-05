package com.example.controller.command.adminCommands.categoriesCommand;

import com.example.controller.command.Command;
import com.example.model.entity.Category;
import com.example.model.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivitiesCategories implements Command {
    AdminService adminService;

    public ActivitiesCategories(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        List<Category> allCategoryList = adminService.getCategoriesList();
        List<Category> categoryOnPage = new ArrayList<>();
        int page = request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page")) - 1;
        int firstElementIndex = page * 3;
        int lastElementIndex = Math.min(firstElementIndex + 3, allCategoryList.size());

        for (int i = firstElementIndex; i < lastElementIndex; i++) {
            categoryOnPage.add(allCategoryList.get(i));
        }

        //List<Integer> pages = new ArrayList<>();
        int amountOfPages = allCategoryList.size() % 3 == 0 ? allCategoryList.size() / 3 : allCategoryList.size() / 3 + 1;
//        for (int i = 1; i <= amountOfPages; i++) {
//            pages.add(i);
//        }

        request.setAttribute("page", amountOfPages);
        request.setAttribute("categoriesList", categoryOnPage);
        return "/view/jsp/role/admin/activitiesCategories.jsp";
    }
}
