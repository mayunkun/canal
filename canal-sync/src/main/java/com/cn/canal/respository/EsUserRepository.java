package com.cn.canal.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cn.canal.respository.bean.User;

/**
 * 
 * @author I'amour solitaire
 *
 */
public interface EsUserRepository extends ElasticsearchRepository<User,String>{
 
}