package sorenrahimi.g4s2m2.services;


import sorenrahimi.g4s2m2.entities.Author;
import sorenrahimi.g4s2m2.entities.BlogPost;
import sorenrahimi.g4s2m2.exceptions.NotFoundException;
import sorenrahimi.g4s2m2.payloads.blogs.NewBlogPostPayload;
import sorenrahimi.g4s2m2.repositories.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogsService {
    @Autowired
    private BlogsRepository blogsRepository;
    @Autowired
    private AuthorsService authorsService;


    public BlogPost save(NewBlogPostPayload body) {
        Author author = authorsService.findById(body.authorId());
        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setReadingTime(body.readingTime());
        newBlogPost.setContent(body.content());
        newBlogPost.setTitle(body.title());
        newBlogPost.setAuthor(author);
        newBlogPost.setCategory(body.category());
        newBlogPost.setCover("http://picsum.photos/200/300");
        return blogsRepository.save(newBlogPost);
    }


    public List<BlogPost> getBlogs() {
        return blogsRepository.findAll();
    }

    public BlogPost findById(int id) {
        return blogsRepository.findById(id).orElseThrow(()-> new
                NotFoundException(id));
    }

    public void findByIdAndDelete(int id){
        BlogPost found = this.findById(id);
        blogsRepository.delete(found);
    }

    public BlogPost findByIdAndUpdate(int id, NewBlogPostPayload body){
        BlogPost found = this.findById(id);

        found.setReadingTime(body.readingTime());
        found.setContent(body.content());
        found.setTitle(body.title());
        found.setCategory(body.category());

        if (found.getAuthor().getId() != body.authorId()) {
            Author newAuthor =
                    authorsService.findById(body.authorId());
            found.setAuthor(newAuthor);
        }
        return blogsRepository.save(found);
    }
    public List<BlogPost> findByAuthor(int authorId){
        Author author = authorsService.findById(authorId);
        return blogsRepository.findByAuthor(author);
    }
}
