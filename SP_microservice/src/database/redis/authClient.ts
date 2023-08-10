import {createClient} from 'redis';

//* Set prod env later
const options = {
    host: 'localhost',
    port: 6739,
    retryStrategy: (times: number) => {
        // reconnect after
        return Math.min (times * 50, 2000)
    }
}

export const authRedisClient = createClient({
    socket: options
});

authRedisClient.on("ready", () => {
    console.log("👍👍 Redis Ready! 👍👍")
})

authRedisClient.on("error", () => {
    console.log("Redis Error!")
})
