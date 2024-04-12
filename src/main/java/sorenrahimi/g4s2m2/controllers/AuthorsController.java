package sorenrahimi.g4s2m2.controllers;

import sorenrahimi.g4s2m2.entities.Author;
import sorenrahimi.g4s2m2.exceptions.BadRequestException;
import sorenrahimi.g4s2m2.payloads.authors.NewAuthorDTO;
import sorenrahimi.g4s2m2.payloads.authors.NewAuthorResponseDTO;
import sorenrahimi.g4s2m2.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




import java.io.IOException;


@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewAuthorResponseDTO saveAuthor(@RequestBody @Validated NewAuthorDTO body, BindingResult validation) throws Exception{
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Author newAuthor = authorsService.save(body);
        return new NewAuthorResponseDTO(newAuthor.getId());

    }

    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "id") String sortBy) {
        return authorsService.getAuthors(page, size, sortBy);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId){
    return authorsService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    private Author findUserByIdAndUpdate(@PathVariable int authorId, @RequestBody Author body){
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. DELETE http://localhost:3001/blogPosts/{userId}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findByIdAndDelete(@PathVariable int authorId){
        authorsService.findByIdAndDelete(authorId);
    }

    @PatchMapping("/{authorId}/avatar")
    public Author uploadAvatar(@RequestParam("avatar")MultipartFile file,
                               @PathVariable int authorId) {
        try {
            return authorsService.uploadAvatar(authorId, file);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

