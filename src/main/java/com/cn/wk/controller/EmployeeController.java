package com.cn.wk.controller;

import com.cn.wk.dao.DepartmentDao;
import com.cn.wk.dao.EmployeeDao;
import com.cn.wk.entities.Department;
import com.cn.wk.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 员工控制层
 * Created by wk on 2019/3/16.
 */
@Controller
public class EmployeeController {

    //自动装配EmployeeDao
    @Autowired
    private EmployeeDao employeeDao;

    //部门
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 查询所有员工返回列表页面
     * @param model
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){

        //获取所有员工信息
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中，页面显示用
        model.addAttribute("emps",employees);
        //thymeleaf自动拼串
        //classpath:/templates/
        return "/emp/list";
    }

    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //获取部门信息，以显示部门下拉列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "/emp/add";
    }

    /**
     * 添加员工，添加完成后返回到员工列表页面
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        //重定向到员工列表页面
        return "redirect:/emps";
    }

    /**
     * 来到编辑页面，回显员工信息
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        //获取部门信息，以显示部门下拉列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //根据id查到员工信息,回显
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        return "/emp/add";
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping("/emp")
    public String editEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
