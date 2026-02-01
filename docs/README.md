# 医院挂号系统文档

## 一、MySQL 建表语句
- 见 `docs/schema.sql`

## 二、后端项目结构（Spring Boot + MyBatis）
```
demo/
├── pom.xml
├── src/main/java/com/example/demo
│   ├── DemoApplication.java
│   ├── common
│   │   ├── ApiResponse.java
│   │   ├── BusinessException.java
│   │   └── GlobalExceptionHandler.java
│   ├── config
│   │   └── MyBatisConfig.java
│   ├── controller
│   │   ├── AuthController.java
│   │   ├── DoctorController.java
│   │   ├── PatientController.java
│   │   └── RegistrationController.java
│   ├── dto
│   │   └── LoginRequest.java
│   ├── entity
│   │   ├── Admin.java
│   │   ├── Doctor.java
│   │   ├── Patient.java
│   │   └── Registration.java
│   ├── mapper
│   │   ├── AdminMapper.java
│   │   ├── DoctorMapper.java
│   │   ├── PatientMapper.java
│   │   └── RegistrationMapper.java
│   └── service
│       ├── AdminService.java
│       ├── DoctorService.java
│       ├── PatientService.java
│       ├── RegistrationService.java
│       └── impl
│           ├── AdminServiceImpl.java
│           ├── DoctorServiceImpl.java
│           ├── PatientServiceImpl.java
│           └── RegistrationServiceImpl.java
└── src/main/resources
    ├── application.yml
    └── mapper
        ├── AdminMapper.xml
        ├── DoctorMapper.xml
        ├── PatientMapper.xml
        └── RegistrationMapper.xml
```

## 三、前端项目结构（Vue3 + Vite + Element Plus + TS）
```
frontend/
├── package.json
├── tsconfig*.json
├── vite.config.ts
├── index.html
└── src
    ├── App.vue
    ├── main.ts
    ├── styles/index.scss
    ├── router/index.ts
    ├── store/user.ts
    ├── layouts/MainLayout.vue
    ├── api
    │   ├── request.ts
    │   └── modules
    │       ├── auth.ts
    │       ├── doctor.ts
    │       ├── patient.ts
    │       └── registration.ts
    └── views
        ├── Login.vue
        ├── Dashboard.vue
        ├── DoctorManage.vue
        ├── PatientManage.vue
        └── RegistrationManage.vue
```

## 四、API 文档（统一返回：`{ code, message, data }`）

| 模块 | 接口 | 方法 | 描述 | 请求参数 | 返回 data 示例 |
| ---- | ---- | ---- | ---- | -------- | -------------- |
| 登录 | `/api/auth/login` | POST | 管理员登录 | `{"username":"","password":""}` | `{"id":1,"username":"admin","realName":"系统管理员"}` |
| 医生 | `/api/doctors` | GET | 查询医生列表 | `name,department` | `[{...Doctor}]` |
| 医生 | `/api/doctors/{id}` | GET | 获取医生详情 | 路径参数 | `Doctor` |
| 医生 | `/api/doctors` | POST | 新增医生 | `Doctor` JSON | `null` |
| 医生 | `/api/doctors/{id}` | PUT | 更新医生 | 路径参数 + `Doctor` | `null` |
| 医生 | `/api/doctors/{id}` | DELETE | 删除医生 | 路径参数 | `null` |
| 患者 | `/api/patients` | GET | 查询患者列表 | `name,phone` | `[{...Patient}]` |
| 患者 | `/api/patients/{id}` | GET | 获取患者详情 | 路径参数 | `Patient` |
| 患者 | `/api/patients` | POST | 新增患者 | `Patient` JSON | `null` |
| 患者 | `/api/patients/{id}` | PUT | 更新患者 | 路径参数 + `Patient` | `null` |
| 患者 | `/api/patients/{id}` | DELETE | 删除患者 | 路径参数 | `null` |
| 挂号 | `/api/registrations` | GET | 查询挂号列表 | `patientName,doctorName,status` | `[{...Registration}]` |
| 挂号 | `/api/registrations/{id}` | GET | 获取挂号详情 | 路径参数 | `Registration` |
| 挂号 | `/api/registrations` | POST | 新增挂号 | `Registration` JSON | `null` |
| 挂号 | `/api/registrations/{id}` | PUT | 修改挂号 | 路径参数 + `Registration` | `null` |
| 挂号 | `/api/registrations/{id}` | DELETE | 取消挂号 | 路径参数 | `null` |

> 说明：`Doctor/Patient/Registration` 字段与实体类保持一致（参见 `entity` 包）。

