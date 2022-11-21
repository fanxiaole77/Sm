package com.example.myapplication.network

data class Login(val username: String, val password: String)

data class Message(val msg: String, val code: Int, val token: String = "")

data class Register(
    val userName: String,
    val password: String,
    val nickName: String,
    val phonenumber: String,
    val idCard: String,
    val sex: String
)
data class HomeService(
    val code: Int?,
    val msg: String?,
    val rows: List<Row>,
    val total: Int?
) {
    data class Row(
        val createBy: Any?,
        val createTime: String?,
        val id: Int?,
        val imgUrl: String?,
        val isRecommend: String?,
        val link: Any?,
        val params: Params?,
        val pid: Any?,
        val remark: Any?,
        val searchValue: Any?,
        val serviceDesc: String?,
        val serviceName: String?,
        val serviceType: String?,
        val sort: Any?,
        val updateBy: Any?,
        val updateTime: String?
    ) {
        class Params
    }
}

data class HomeBanner(
    val code: Int?,
    val msg: String?,
    val rows: List<Row?>,
    val total: Int?
) {
    data class Row(
        val advImg: String?,
        val advTitle: String?,
        val appType: String?,
        val createBy: String?,
        val createTime: String?,
        val id: Int?,
        val params: Params?,
        val remark: Any?,
        val searchValue: Any?,
        val servModule: String?,
        val sort: Int?,
        val status: String?,
        val targetId: Int?,
        val type: String?,
        val updateBy: String?,
        val updateTime: String?
    ) {
        class Params
    }
}

data class NewsClass(
    val code: Int?,
    val `data`: List<Data>,
    val msg: String?
) {
    data class Data(
        val appType: String?,
        val createBy: Any?,
        val createTime: Any?,
        val id: Int?,
        val name: String?,
        val params: Params?,
        val parentId: Any?,
        val remark: Any?,
        val searchValue: Any?,
        val sort: Int?,
        val status: String?,
        val updateBy: Any?,
        val updateTime: Any?
    ) {
        class Params
    }
}

data class NewsList(
    val code: Int?,
    val msg: String?,
    val rows: List<Row?>,
    val total: Int?
) {
    data class Row(
        val appType: String?,
        val commentNum: Int?,
        val content: String?,
        val cover: String?,
        val createBy: String?,
        val createTime: String?,
        val hot: String?,
        val id: Int?,
        val likeNum: Int?,
        val params: Params?,
        val publishDate: String?,
        val readNum: Int?,
        val remark: Any?,
        val searchValue: Any?,
        val status: String?,
        val subTitle: Any?,
        val tags: Any?,
        val title: String?,
        val top: String?,
        val type: String?,
        val updateBy: String?,
        val updateTime: String?
    ) {
        class Params
    }
}
data class NewContent(
    val code: Int?,
    val `data`: Data,
    val msg: String?
) {
    data class Data(
        val appType: String?,
        val commentNum: Int?,
        val content: String?,
        val cover: String?,
        val createBy: String?,
        val createTime: String?,
        val hot: String?,
        val id: Int?,
        val likeNum: Int?,
        val params: Params?,
        val publishDate: String?,
        val readNum: Int?,
        val remark: Any?,
        val searchValue: Any?,
        val status: String?,
        val subTitle: Any?,
        val tags: Any?,
        val title: String?,
        val top: String?,
        val type: String?,
        val updateBy: String?,
        val updateTime: String?
    ) {
        class Params
    }
}
data class HospBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val hospitalId: Int,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class HospList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val brief: String,
        val createBy: Any,
        val createTime: Any,
        val hospitalName: String,
        val id: Int,
        val imgUrl: String,
        val level: Int,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class HospContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val brief: String,
        val createBy: Any,
        val createTime: Any,
        val hospitalName: String,
        val id: Int,
        val imgUrl: String,
        val level: Int,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class UserInfo(
    val code: Int,
    val msg: String,
    val user: User
) {
    data class User(
        val avatar: String,
        val balance: Double,
        val email: String,
        val idCard: String,
        val nickName: String,
        val phonenumber: String,
        val score: Int,
        val sex: String,
        val userId: Int,
        val userName: String
    )
}
data class PostAdd(
    val address: String,
    val birthday: String,
    val cardId: String,
    val name: String,
    val sex: String,
    val tel: String
)
data class HospUser(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val address: String,
        val birthday: String,
        val cardId: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val imgUrl: Any,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sex: String,
        val tel: String,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        class Params
    }
}
data class Cate(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val categoryName: String,
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val money: Double,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val type: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class HospApi(
    val categoryId: Int,
    val money: Int,
    val patientName: String,
    val reserveTime: String,
    val type: Int
)

data class YY(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val categoryId: Int,
        val categoryName: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val money: Double,
        val orderNo: String,
        val params: Params,
        val patientName: String,
        val remark: Any,
        val reserveTime: String,
        val searchValue: Any,
        val status: Any,
        val type: String,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        class Params
    }
}

data class AdBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class NewsType(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class GarNewsList(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val author: String,
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val title: String,
        val type: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class GarNewsContent(
    val code: Int,
    val `data`: Data,
    val msg: String
) {
    data class Data(
        val author: String,
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val title: String,
        val type: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class GarComment(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val content: String,
        val createBy: Any,
        val createTime: String,
        val id: Int,
        val newsId: Int,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any,
        val userId: Int
    ) {
        class Params
    }
}

data class ReleaseComment(
    val content: String,
    val newsId: Int
)

data class GarLjType(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
) {
    data class Row(
        val createBy: Any,
        val createTime: Any,
        val guide: String,
        val id: Int,
        val imgUrl: String,
        val introduce: String,
        val name: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}

data class GarLjBanner(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val imgUrl: String,
        val params: Params,
        val remark: Any,
        val searchValue: Any,
        val sort: Int,
        val title: String,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}
data class HotTitle(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val keyword: String,
        val params: Params,
        val remark: Any,
        val searchTimes: Int,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
    ) {
        class Params
    }
}