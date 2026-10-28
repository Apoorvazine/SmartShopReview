
package com.shopreview.controller;

import com.shopreview.entity.Review;
import com.shopreview.repository.ReviewRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // All approved comment suggestions
    private static final List<String> ALL_COMMENTS = Arrays.asList(
        "I found some pretty bangles and jewellery designs here.",
        "There was a nice variety of jewellery to choose from.",
        "The staff was polite and helpful during my visit.",
        "I had a pleasant experience shopping at this store.",
        "There were different designs to explore within my budget.",
        "I liked the collection of bangles and accessories.",
        "The staff helped me look at different options.",
        "I found some lovely items for everyday use.",
        "The shop has jewellery and beauty products in one place.",
        "I enjoyed browsing through the available collection.",
        "There were plenty of designs to look through.",
        "I found what I was looking for during my visit."
    );

    // Show 3 random comment options on each page load
    @GetMapping("/review")
    public String showReviewPage(Model model) {

        List<String> options = new ArrayList<>(ALL_COMMENTS);
        Collections.shuffle(options);

        model.addAttribute("commentOptions", options.subList(0, 3));

        return "review";
    }

    // Save the selected rating and one selected comment
    @PostMapping("/submit-review")
    public String submitReview(
            @RequestParam int rating,
            @RequestParam String comment,
            RedirectAttributes redirectAttributes) {

        if (rating != 3 && rating != 4) {
            redirectAttributes.addFlashAttribute(
                "message", "Please select a valid rating."
            );
            return "redirect:/review";
        }

        if (!ALL_COMMENTS.contains(comment)) {
            redirectAttributes.addFlashAttribute(
                "message", "Please select one of the available comments."
            );
            return "redirect:/review";
        }

        Review review = new Review();
        review.setRating(rating);
        review.setFeedback(comment);
        review.setCategory("General");

        reviewRepository.save(review);

        return "redirect:/thank-you";
    }

    @GetMapping("/thank-you")
    public String showThankYouPage(Model model) {

        model.addAttribute(
            "googleReviewUrl",
            "https://share.google/Zqh18FuS0JJtkT4KQ"
        );

        return "thank-you";
    }
}
