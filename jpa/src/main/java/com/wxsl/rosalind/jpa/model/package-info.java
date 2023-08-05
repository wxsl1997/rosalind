@TypeDefs({
        @TypeDef(name = "IntEnum", typeClass = IntEnumType.class),
        @TypeDef(name = "Json", typeClass = JsonType.class)
})
package com.wxsl.rosalind.jpa.model;

import com.wxsl.rosalind.jpa.util.IntEnumType;
import com.wxsl.rosalind.jpa.util.JsonType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;