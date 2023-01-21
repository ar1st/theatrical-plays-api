package aris.thesis.theatricalplaysapi.dtos

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class UserDTO(var email: String, var password: String, var authorities: List<String>): DataTransferObject