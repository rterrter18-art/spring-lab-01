package kz.iitu.springlab.web;

import jakarta.servlet.http.HttpServletResponse;
import kz.iitu.springlab.catalog.Book;
import kz.iitu.springlab.catalog.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookPageController {

    private final BookService service;

    public BookPageController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String author,
            Model model) {

        model.addAttribute("books", service.findAll(author));
        model.addAttribute("author", author);
        model.addAttribute("title", "Book Catalogue");

        return "books/list";
    }

    @GetMapping("/{id}")
    public String details(
            @PathVariable long id,
            Model model,
            HttpServletResponse response) {

        var book = service.findById(id);

        if (book.isEmpty()) {
            response.setStatus(404);
            model.addAttribute("id", id);

            return "books/not-found";
        }

        model.addAttribute("book", book.get());

        return "books/details";
    }

    @PostMapping
    public String create(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam int year,
            RedirectAttributes redirect) {

        Book saved = service.create(
                new Book(null, title, author, year)
        );

        redirect.addFlashAttribute(
                "message",
                "Book \"" + saved.title() + "\" has been added"
        );

        return "redirect:/books";
    }
}