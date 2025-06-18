package com.capston_design.fkiller.itoms.user_service.service;


import com.capston_design.fkiller.itoms.user.UserProto;
import com.capston_design.fkiller.itoms.user.UserServiceGrpc;
import com.capston_design.fkiller.itoms.user_service.apiPayload.code.status.SuccessStatus;
import com.capston_design.fkiller.itoms.user_service.model.User;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void getRandomCreatorInfo(Empty request, StreamObserver<UserProto.CommonCreatorInfoResponse> responseObserver) {
        User randomCreatorUser = userService.getRandomCreatorUser();

        UserProto.CommonCreatorInfoResponse response = UserProto.CommonCreatorInfoResponse.newBuilder()
            .setIsSuccess(true)
            .setCode(SuccessStatus._OK.getCode())
            .setMessage(SuccessStatus._OK.getMessage())
                .setResult(
                        UserProto.CreatorInfoResponse.newBuilder()
                                .setId(randomCreatorUser.getId().toString())
                                .setName(randomCreatorUser.getName())
                                .setCategory(randomCreatorUser.getCategory().toString())
                                .build()
                )
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getRandomRequesterUser(Empty request, StreamObserver<UserProto.UserCreateResponse> responseObserver) {
        User randomRequesterUser = userService.getRandomRequesterUser();

        UserProto.UserCreateResponse response = UserProto.UserCreateResponse.newBuilder()
                .setId(randomRequesterUser.getId().toString())
                .setName(randomRequesterUser.getName())
                .setCategory(randomRequesterUser.getCategory().toString())
                .build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
