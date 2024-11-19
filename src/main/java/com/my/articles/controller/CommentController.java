package com.my.articles.controller;

import com.my.articles.dto.CommentDTO;
import com.my.articles.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("articles/{article_id}/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("create")
    public String createComment(@PathVariable("article_id")Long article_id, CommentDTO commentDTO, RedirectAttributes redirectAttributes){
        String url = "redirect:/articles/" +article_id;
        commentService.createComment(article_id, commentDTO);
        redirectAttributes.addFlashAttribute("commentMsg", "댓글이 등록되었습니다!");
        return url;
    }

    @GetMapping("{id}/update")
    public String updateCommentView(@PathVariable("article_id")Long article_id, @PathVariable("id")Long id, Model model) {
        model.addAttribute("article_id", article_id);
        model.addAttribute("comment", commentService.findById(id));
        return "/articles/update_comment";
    }

    @PostMapping("update")
    public String updateComment(@PathVariable("article_id")Long article_id, CommentDTO commentDTO, RedirectAttributes redirectAttributes) {
        String url = "redirect:/articles/" +article_id;
        commentService.updateComment(commentDTO);
        redirectAttributes.addFlashAttribute("commentMsg", "댓글이 수정되었습니다!");
        return  url;
    }

    @GetMapping("{id}/delete")
    public String deleteComment(@PathVariable("article_id")Long article_id, @PathVariable("id")Long id, RedirectAttributes redirectAttributes) {
        String url = "redirect:/articles/" +article_id;
        commentService.deleteComment(id);
        redirectAttributes.addFlashAttribute("commentMsg", "댓글이 삭제되었습니다!");
        return  url;
    }
}
