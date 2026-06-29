package com.linalingling.bbb.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;

import java.time.LocalDateTime;

@Entity
@Table(name="behavior_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BehaviorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int goalId; // 修改：從原本可能的 characterId 改為 goalId，因為紀錄屬於計畫
    private String action; // 修改：由 behaviorType 改為 action，對齊 DB 欄位
    private String note;
    private BigDecimal baseValue;
    private BigDecimal calculatedPoints;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}




