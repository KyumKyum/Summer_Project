import { UUID } from "crypto"
import { authRedisClient } from "../../database/redis/authClient"
import { AuthResponse } from "../../types/authResponse"
import { DBDataSource } from "../../database/postgresql/datasource";
import { User } from "../../database/postgresql/Entities/User";

export const checkValidReq = async (key: string): Promise<AuthResponse> => {
    try{
        //* Check value exists.
        // const userId: string | null = await authRedisClient.get(key);
        //
        // if(userId == null){
        //     throw new Error("Not Valid Key");
        // }
        //
        // const user:User | null = await DBDataSource.manager.findOneBy(User, {
        //     keyVal: key
        // });
        //
        // if(user === null){
        //     throw new Error("No Such User");
        // }

        const authRes: AuthResponse = {
            ok:true,
            message: "SUCCESS"
        }

        return authRes;
    }catch(e: any){
        const authRes: AuthResponse = {
            ok: false,
            message: (e instanceof Error) ? e.message : "UNKNOWN" 
        };
        return authRes;
    }
}