package com.example.demo.service.support;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * CRUD service that uses a MyBatis Mapper implementation You should extend it
 * and inject your Repository bean by overriding setRepository
 *
 * @param <T>  Your resource class to manage, usually an entity class
 * @param <ID> Resource id type, usually Long or String
 * @param <R>  The repository class
 */
public class CrudServiceImpl<T, ID extends Serializable, R extends CrudMapper<T, ID>>
        implements CrudService<T, ID> {

    protected R repository;

    /**
     * @param repository the repository to set
     */
    public void setRepository(R repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public T create(T resource) {
        repository.insertSelective(resource);
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public T update(T resource) {

        // 根据更新对象类型是否需要全量更新，判断是否是全更新还是有值更新
        repository.updateByPrimaryKey(resource);

        return resource;
    }

    /**
     * save resource data
     *
     * @param id
     * @param resource
     * @return resource saved
     */
    @Override
    @Transactional(readOnly = false)
    public T saveSelective(ID id, T resource) {

        if (Objects.isNull(id)) {

            create(resource);

        } else {
            update(resource);
        }
        return resource;
    }

//    /**
//     * save resource datas
//     *
//     * @param primaryKeyAccessor
//     * @param resources
//     * @return
//     */
//    @Transactional(readOnly = false)
//    @Override
//    public List<? extends T> saveSelective(
//            PrimaryKeyAccessor<T, ID> primaryKeyAccessor,
//            List<? extends T> resources) {
//
//        if (CollectionUtils.isNotEmpty(resources)) {
//            for (T data : resources) {
//                ID id = primaryKeyAccessor.access(data);
//                saveSelective(id, data);
//            }
//        }
//
//        return resources;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(ID id) {
        repository.deleteByPrimaryKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) {

        T entity = repository.selectByPrimaryKey(id);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> getOne(ID id) {

        T entity = repository.selectByPrimaryKey(id);

        return Optional.ofNullable(entity);
    }

}
