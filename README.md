[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![码云](https://img.shields.io/badge/Gitee--yellow.svg?style=social&logo=data:image/svg+xml;base64,PHN2ZyB0PSIxNTc0ODM3MTM4ODM3IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHAtaWQ9IjE3NzAiICAgICB3aWR0aD0iMTYiIGhlaWdodD0iMTYiPiAgICA8cGF0aCBkPSJNODkxIDQyOC44SDQ2NS44Yy0yMC40IDAtMzcgMTYuNS0zNyAzN3Y5Mi40YzAgMjAuNCAxNi41IDM3IDM3IDM3aDI1OC45YzIwLjQgMCAzNyAxNi42IDM3IDM3djE4LjRjMCA2MS4zLTQ5LjcgMTEwLjktMTEwLjkgMTEwLjlIMjk5LjRjLTIwLjQgMC0zNy0xNi42LTM3LTM3VjM3My4yYzAtNjEuMyA0OS43LTExMC45IDExMC45LTExMC45aDUxNy42YzIwLjQgMCAzNy0xNi41IDM3LTM3bDAuMS05Mi4zYzAtMjAuNC0xNi41LTM3LTM3LTM3SDM3My4zQzIyMC4yIDk2IDk2IDIyMC4yIDk2IDM3My4zVjg5MWMwIDIwLjQgMTYuNiAzNyAzNyAzN2g1NDUuNEM4MTYuMiA5MjggOTI4IDgxNi4zIDkyOCA2NzguNFY0NjUuOGMwLTIwLjQtMTYuNi0zNy0zNy0zN3oiICAgICAgICAgIGZpbGw9IiNkODFlMDYiIHAtaWQ9IjE3NzEiPjwvcGF0aD48L3N2Zz4=)](https://gitee.com/weihongbin/luban-api.git)
[![Github](https://img.shields.io/badge/GitHub--yellow.svg?style=social&logo=github)](https://github.com/luban-h5/springboot2-mybatis-plus-api-for-luban.git)


# springboot2-mybatis-plus-api-for-luban

* #!zh: 为[鲁班H5](https://github.com/ly525/luban-h5) 提供 由 [Spring Boot](https://spring.io/projects/spring-boot) 驱动的后端 API
* #!zh: 现在仍然在完善中，非常欢迎 PR，如果您想参与贡献，可以直接 Pull Request。也可以和作者直接联系, [联系方式](https://github.com/ly525/luban-h5#%E4%BA%A4%E6%B5%81%E7%BE%A4)

---

* #!en: Demo API for [Luban-H5-Editor-Module](https://github.com/ly525/luban-h5) powered by [Spring Boot](https://spring.io/projects/spring-boot)
* #!en It is still working is progress, so pr is welcome!(now it's just a demo)

#### 相关文档
* [说明文档(WIP/完善中)](https://www.yuque.com/xpm1xa/rgf7kz/xkm4aq)


### TODO
> pr is welcome!

- [x] Get Work By Id
- [x] Get All Works
- [x] Update Work
- [x] Create Work
- [x] Delete Work
- [ ] Upload Work Cover
- [ ] Cors Proxy
- [x] Set Work as Template
- [ ] create Work based on Template


### Development
1. 使用 init.sql 初始化数据库
2. 修改 src/main/resources/application-dev.yml  中的 mysql 相关配置
3. 修改 src/main/resources/application-prod.yml 中的 mysql 相关配置



>  #!zh 前后端联调开发

> * [Node、yarn 安装教程](https://github.com/ly525/luban-h5/blob/dev/docs/zh/getting-started/quick-start.md#nodeyarnnpm%E5%AE%89%E8%A3%85)
> * 请使用 yarn 安装依赖，而非 npm，原因参见 [#92](https://github.com/ly525/luban-h5/issues/92) 和 [#101](https://github.com/ly525/luban-h5/issues/101) 
> * 安装前端项目依赖，[前端开发文档](https://github.com/ly525/luban-h5/blob/dev/docs/zh/getting-started/quick-start.md)：


```bash
git clone https://github.com/ly525/luban-h5
cd luban-h5/front-end/h5
yarn install 
# #!en modify `target` in `vue.config.js`
# #!zh 修改 vue.config.js 中的 target 变量，比如：const target = 'http://127.0.0.1:8888'，
# #!zh 8888 为 spring-boot-api-for-editor 提供服务的端口, 修改完毕之后，运行下面的命令，即可启动前端服务进行联调 
yarn serve # 是 serve 不是 server!
```
另外开一个terminal

open another terminal
```shell script
git clone https://github.com/luban-h5/springboot2-mybatis-plus-api-for-luban.git
cd  springboot2-mybatis-plus-api-for-luban
mvn spring-boot:run
``` 
接口文档 地址 
```text
http://[host]:[port]/doc.html
```
### 启动 Spring Boot 项目，联调开始
#### 技术栈（当前）
1. [SpringBoot](https://spring.io/projects/spring-boot) 
    - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run". We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration.
2. [Mybatis Plus](https://mybatis.plus/) 
    - MyBatis-Plus（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。
3. [knife4j](https://doc.xiaominfo.com/) 
    - knife4j是为Java MVC框架集成Swagger生成Api文档的增强解决方案
4. [p6spy](https://github.com/p6spy/p6spy)
    - P6Spy is a framework that enables database data to be seamlessly intercepted and logged with no code changes to the application.
    