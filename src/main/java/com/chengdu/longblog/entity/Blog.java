package com.chengdu.longblog.entity;

import com.baomidou.mybatisplus.annotation.*;


import java.sql.Date;

import java.util.ArrayList;

import java.util.List;


@TableName(value = "t_blog")
public class Blog {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String title;            //标题
    private String content;         //内容
    private String firstPicture;    //首图
    private String flag;            //标记
    private Integer views;          //浏览次数
    private boolean appreciation;   //赞赏开启
    private boolean shareStatement; //版权开启
    private boolean commentabled;   //评论开启
    private boolean published;      //是否发布
    private boolean recommend;      //是否推荐  0:true   1:false
    @TableLogic
    @TableField(value = "del_tag")
    private Integer delTag;        //实现逻辑删除
    /*@Temporal(TemporalType.TIMESTAMP)*/
    private Date createTime;        //创建时间

    private Date updateTime;        //更新时间


    @TableField(exist = false)
    private Type type;
    @TableField(value = "type_id")
    private long typeId;   //此属性对应数据库的type_id,获取到存放type属性的id
    @TableField(value = "user_id")
    private long userId;
    @TableField(exist = false)
    private User user;


    @TableField(exist = false)
    private List<Tag> tags = new ArrayList<>();
    @TableField(value = "tag_id")
    private String tagIds;  //存放前端传输过来的tag.id字符串，不进行持久化操作


    @TableField(exist = false)
    private List<Comment> comments = new ArrayList<>();



    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", delTag=" + delTag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = getUser().getId();
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId() {
        this.typeId = type.getId();
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getDelTag() {
        return delTag;
    }

    public void setDelTag(Integer delTag) {
        this.delTag = delTag;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
