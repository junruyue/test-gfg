package com.cetc27.gfg.rwgl.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GFG_SJCP_CPST")
@Data
public class CPST implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(50)")
    @GenericGenerator(name = "entity-uuid",strategy = "uuid")
    @GeneratedValue(generator = "entity-uuid")
    private String entityId;//实体id
    @Column(columnDefinition = "nvarchar(50)")
    private String productId;//产品id
    @Column(columnDefinition = "nvarchar(100)")
    private String productName;//产品名称
    @Column(columnDefinition = "nvarchar(10)")
    private String fileExtension;//文件扩展名
    @Column(nullable = true,columnDefinition = "bigint")
    private long fileSize;//文件大小
    @Column(columnDefinition = "blob")
    private String fileContent;//文件内容: 不填
    @Column(nullable = true)
    private int fileType;//文件类型: 不知道填什么
    @Column(columnDefinition = "nvarchar(10)")
    private String storageClass;//存储类型
    @Column(columnDefinition = "nvarchar(500)")
    private String relativePath;//相对路径
    @Column(columnDefinition = "nvarchar(200)")
    private String rootPath;//绝对路径
    @Column(nullable = true)
    private int shareLevel;//共享等级
}
