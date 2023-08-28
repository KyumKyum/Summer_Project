import {UUID} from "crypto";

export type genAvatarReq = {
    userId: UUID | String,
    name: string
    size:number
    keyVal: string
}