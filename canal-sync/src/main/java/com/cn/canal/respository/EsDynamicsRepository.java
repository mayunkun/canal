package com.cn.canal.respository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.cn.canal.respository.bean.Dynamics;


/**
 * 
 * @author I'amour solitaire
 *
 */
@Repository
public interface EsDynamicsRepository extends ElasticsearchRepository<Dynamics,String>{
 
}