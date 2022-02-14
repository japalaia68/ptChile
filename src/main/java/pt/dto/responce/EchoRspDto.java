package pt.dto.responce;

import lombok.Data;

import java.io.Serializable;


@Data

public class EchoRspDto implements Serializable {

//        "timestamp": "<current date and time>",
    private Long timestamp;
//        "hostname": "<Name of the host (IP also accepted)>",
    private String hostname;
//        "engine": "<Name and/or version of the engine running>",
    private String engine;
//        "visitor ip": "<the IP address of the visitor>"
    private String visitorIp;
}


