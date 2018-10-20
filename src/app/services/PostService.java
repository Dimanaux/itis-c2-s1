package app.services;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(int id);
    Post createPost(String title, User user, String text);
}
