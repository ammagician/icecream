package com.apang.icecream.core.services.impl;

import com.apang.icecream.core.domain.bo.Document;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.apang.icecream.core.mapper.DocumentMapper;
import com.apang.icecream.core.services.IDocumentService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {

}
