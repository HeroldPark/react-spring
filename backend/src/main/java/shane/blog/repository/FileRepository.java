package shane.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shane.blog.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
