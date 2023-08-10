import { DataSource } from "typeorm";

//* Need to add env
export const DBDataSource: DataSource = new DataSource({
    type: 'postgres',
    host: 'localhost',
    password: 'PASSWORD',
    username: 'kyumericano',
    port: 5432,
    database: 'testdb',
    entities: ["./Entities/*.ts"]
})