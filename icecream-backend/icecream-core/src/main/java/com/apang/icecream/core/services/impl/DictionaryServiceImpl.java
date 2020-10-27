package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Dictionary;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apang.icecream.core.mapper.DictionaryMapper;
import com.apang.icecream.core.services.IDictionaryService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

}
