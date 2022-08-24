package com.ssg.newbie.entity;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.domain.user.NormalUser;
import com.ssg.newbie.domain.user.SmileClubUser;
import com.ssg.newbie.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private UserClubType clubType;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User toDto() {
        return switch (clubType) {
            case SMILE_CLUB -> new SmileClubUser(this.name);
            case NORMAL -> new NormalUser(this.name);
        };
    }
}
