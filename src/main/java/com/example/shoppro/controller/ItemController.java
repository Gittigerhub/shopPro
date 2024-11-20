package com.example.shoppro.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ItemController {
    // 이전에 계속 연습하던 BoardController라고 생각하면 됨


    @GetMapping("/admin/item/new")
    public String itemFrom() {

        return "item/itemForm";
    }



}
