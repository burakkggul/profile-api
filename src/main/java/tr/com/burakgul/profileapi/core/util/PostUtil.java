package tr.com.burakgul.profileapi.core.util;

import tr.com.burakgul.profileapi.model.dto.blog.PostResponse;
import tr.com.burakgul.profileapi.model.entity.blog.Post;

public class PostUtil {

    private PostUtil(){
    }

    public static void calculateAndSetReadingMinute(Post post){
        post.setReadingMinute(post.getContent().length()/6 / 60);
    }

    public static void calculateAndSetCommentAndClapCount(Post post, PostResponse postResponse){
        postResponse.setClapCount(post.getClaps().size());
        postResponse.setCommentCount(post.getComments().size());
    }
}
