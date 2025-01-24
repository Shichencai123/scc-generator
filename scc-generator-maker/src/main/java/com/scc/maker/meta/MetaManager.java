package com.scc.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * className:  MetaManager
 * Package:  com.scc.maker.meta
 * Description: 读取配置文件数据到Meta
 *
 * @Date: 2024/11/5 22:52
 * @Author: shicc
 */
public class MetaManager {
    private static volatile Meta meta;
    private MetaManager(){}

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    initMeta();
                }
            }
        }
        return meta;
    }

    private static void initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        meta = JSONUtil.toBean(metaJson, Meta.class);
        MetaValidator.doValidAndFill(meta);
    }

}
