//package com.example.demo.controller;
//
//public class NoticeController {
//
//}

package com.example.demo.controller;

import com.example.demo.model.Notice;
import com.example.demo.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@CrossOrigin("*")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // Admin → Add Notice
    @PostMapping
    public Notice addNotice(@RequestBody Notice notice) {
        return noticeService.addNotice(notice);
    }

    // Residents → View Notices
    @GetMapping
    public List<Notice> getAllNotices() {
        return noticeService.getAllNotices();
    }

    // Admin → Delete Notice
    @DeleteMapping("/{id}")
    public String deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return "Notice deleted successfully!";
    }
}