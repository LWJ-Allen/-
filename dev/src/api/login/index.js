import service from "@/utils/request.js";
import axios from "axios";

const login = async (data) => {
    const response = await service.post('/login', data)
}