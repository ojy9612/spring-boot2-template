package com.zeki.springboot2template.security.em;

import com.zeki.springboot2template.domain._common.utils.validation.em.DescriptionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UserRole implements DescriptionEnum {
    USER("일반"),
    ADMIN("관리자"),
    ;

    private String description;

    UserRole(String description) {
        this.description = description;
    }

    public static UserRole of(String description) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getDescription().equalsIgnoreCase(description)) {
                return userRole;
            }
        }
        return null;
    }

}
