//package com.example.demo.service;
//
//public class NoticeService {
//
//}

package com.example.demo.service;

import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    // Add Notice
    public Notice addNotice(Notice notice) {
        notice.setCreatedAt(LocalDateTime.now());
        return noticeRepository.save(notice);
    }

    // Get All Notices
    public List<Notice> getAllNotices() {
        return noticeRepository.findAllByOrderByCreatedAtDesc();
    }

    // Delete Notice
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}