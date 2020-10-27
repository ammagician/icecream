package com.apang.icecream.systemmanager.services.impl;

import com.apang.icecream.systemmanager.domain.bo.Page;
import com.apang.icecream.systemmanager.mapper.PageMapper;
import com.apang.icecream.systemmanager.services.IPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author andy.pang
 * @since 2019-09-06
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements IPageService {

}
