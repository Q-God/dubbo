/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * Parameter
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Parameter {

    /**
     * 键 指定url追加参数时的参数key
     * <p>
     * Specify the parameter key when append parameters to url
     */
    String key() default "";

    /**
     * 是都必填 如果required=true，则追加到url时该值不能为空
     * <p>
     * If required=true, the value must not be empty when append to url
     */
    boolean required() default false;

    /**
     * 是否忽略 如果exclusion=true，则在url附加参数时忽略它
     * <p>
     * If excluded=true, ignore it when append parameters to url
     */
    boolean excluded() default false;

    /**
     * 是否转义 如果 escaped=true，则在 url 中追加参数时转义
     * <p>
     * if escaped=true, escape it when append parameters to url
     */
    boolean escaped() default false;

    /**
     * 是否为属性 如果attribute=false，则在处理refresh()/getMetadata()/equals()/toString()时忽略它
     * 目前用于《事件通知》http://dubbo.apache.org/zh-cn/docs/user/demos/events-notify.html
     * <p>
     * If attribute=false, ignore it when processing refresh()/getMetadata()/equals()/toString()
     */
    boolean attribute() default true;

    /**
     * 如果append=true，则将新值附加到现有值而不是覆盖它。 是否拼接默认属性，参见
     * {@link org.apache.dubbo.config.AbstractConfig#appendParameters0(Map, Object, String, boolean)} 方法。
     * <p>
     * 我们来看看 `#append() = true` 的属性，有如下四个： + {@link org.apache.dubbo.config.AbstractInterfaceConfig#getFilter()} +
     * {@link org.apache.dubbo.config.AbstractInterfaceConfig#getListener()} + {@link org.apache.dubbo.config.AbstractInterfaceConfig#getFilter()} +
     * {@link org.apache.dubbo.config.AbstractInterfaceConfig#getListener()} + {@link org.apache.dubbo.config.AbstractInterfaceConfig#getFilter()} +
     * {@link org.apache.dubbo.config.AbstractInterfaceConfig#getListener()} 那么，以 AbstractServiceConfig 举例子。
     * <p>
     * 我们知道 ProviderConfig 和 ServiceConfig 继承 AbstractServiceConfig 类，那么 `filter` , `listener` 对应的相同的键。 下面我们以 `filter`
     * 举例子。
     * <p>
     * 在 ServiceConfig 中，默认会<b>继承</b> ProviderConfig 配置的 `filter` 和 `listener` 。 所以这个属性，就是用于，像 ServiceConfig 的这种情况，从
     * ProviderConfig 读取父属性。
     * <p>
     * 举个例子，如果 `ProviderConfig.filter=aaaFilter` ，`ServiceConfig.filter=bbbFilter` ，最终暴露到 Dubbo URL 时，参数为
     * `service.filter=aaaFilter,bbbFilter` 。
     * <p>
     * If append=true, append new value to exist value instead of overriding it.
     */
    boolean append() default false;
}
