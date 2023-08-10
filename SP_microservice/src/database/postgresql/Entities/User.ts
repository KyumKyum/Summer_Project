import { Entity, PrimaryGeneratedColumn, Column } from "typeorm";

@Entity()
export class User {
    @PrimaryGeneratedColumn("uuid")
    id!: string

    @Column("string")
    username!: string

    @Column("string")
    ident!: string

    @Column("string")
    password!: string

    @Column("string")
    keyVal!: string
}