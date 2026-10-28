
package com.shopreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    private static final String GOOGLE_REVIEW_URL =
            "https://share.google/Zqh18FuS0JJtkT4KQ";

    @GetMapping("/review")
    public String showReviewPage() {
        return "review";
    }

    @PostMapping("/submit-review")
    public String submitReview() {
        return "redirect:/thank-you";
    }

    @GetMapping("/thank-you")
    public String showThankYouPage(Model model) {
        model.addAttribute("googleReviewUrl", GOOGLE_REVIEW_URL);
        return "thank-you";
    }
}
