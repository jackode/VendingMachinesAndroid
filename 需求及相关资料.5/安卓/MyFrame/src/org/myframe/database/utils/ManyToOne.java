/*
 * Copyright (c) 2014,KJFrameForAndroid Open Source Project,张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.myframe.database.utils;

/**
 * 多对一的字段<br>
 * 
 * <b>创建时间</b> 2014-8-15
 * 
 * @author kymjs (https://github.com/kymjs)
 * @version 1.0
 */
public class ManyToOne extends Property {

    private Class<?> manyClass;

    public Class<?> getManyClass() {
        return manyClass;
    }

    public void setManyClass(Class<?> manyClass) {
        this.manyClass = manyClass;
    }

}
