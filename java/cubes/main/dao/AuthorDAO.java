package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Author;
import cubes.main.entity.Blog;

public interface AuthorDAO {
	 public List<Author> getAuthorList();
	 public void deleteAuthor (int id);
	 public void saveAuthor(Author author);
	 public Author getAuthor(int id);

}
