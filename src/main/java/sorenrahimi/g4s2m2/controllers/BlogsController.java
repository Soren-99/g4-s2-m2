package sorenrahimi.g4s2m2.controllers;

import sorenrahimi.g4s2m2.entities.BlogPost;
import sorenrahimi.g4s2m2.exceptions.BadRequestException;
import sorenrahimi.g4s2m2.payloads.blogs.NewBlogPostPayload;
import sorenrahimi.g4s2m2.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost saveBlog(@RequestBody @Validated NewBlogPostPayload body,
                             BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return blogsService.save(body);
    }

    @GetMapping("")
    public List<BlogPost> getBlogs(@RequestParam(required = false)
                                   Integer authorId) {
        if (authorId != null) return blogsService.findByAuthor(authorId);
        else return blogsService.getBlogs();
    }

    @GetMapping("/{blogId}")
    public BlogPost findById(@PathVariable int blogId){
        return blogsService.findById(blogId);
    }

    @PutMapping("/{blogId}")
    public BlogPost findAndUpdate(@PathVariable int blogId,
                                  @RequestBody NewBlogPostPayload body){
        return blogsService.findByIdAndUpdate(blogId, body);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int blogId){
        blogsService.findByIdAndDelete(blogId);
    }
}
