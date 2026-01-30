官方文档：[Kubernetes 文档 | Kubernetes](https://kubernetes.io/zh-cn/docs/home/)

##  一、集群管理命令

```
kubectlcluster-info：显示集群的基本信息，比如 Kubernetes 控制平面的地址等 。

kubectl get nodes：列出集群中的所有节点，展示节点的名称、状态、角色等 。

kubectl describe node <node-name>：查看指定节点的详细信息，包括资源分配、运行的 Pod 等 。

kubectl cordon <node-name>：标记节点为不可调度，一般用于节点维护 。

kubectl uncordon <node-name>：取消节点不可调度标记，恢复正常调度 。

kubectl drain <node-name> --ignore-daemonsets：排空节点，驱逐节点上的 Pod（忽略 DaemonSet 类型的 Pod），为维护做准备 。

kubectl [top](https://so.csdn.net/so/search?q=top&spm=1001.2101.3001.7020) nodes：查看集群中各个节点的资源使用情况，如 CPU、内存等 。

kubectl version：查看 kubectl 客户端和 Kubernetes 服务端的版本信息 。

kubectl api-versions：输出 Kubernetes API 支持的所有版本 。

kubectl api-resources：列出 Kubernetes API 支持的所有资源类型 。
```

## 二、命名空间管理命令
```
kubectl get namespaces：列出所有命名空间 。

kubectl create namespace <namespace-name>：创建一个新的命名空间 。

kubectl delete namespace <namespace-name>：删除指定的命名空间及其下的所有资源 。

kubectl describe namespace <namespace-name>：查看命名空间的详细信息，包括资源配额等 。

kubectl config set-context --current --namespace=<namespace>：切换当前操作的命名空间 。
```

## 三、资源管理命令

### （一）资源查看

```

kubectl get <resource-type>：列出指定类型的资源，比如kubectl get pods列出所有 Pod 。

kubectl get <resource-type> -n <namespace>：列出指定命名空间下的资源 。

kubectl get <resource-type> <resource-name>：查看指定名称的资源 。

kubectl get <resource-type> -l <label>：通过标签筛选资源，如kubectl get pods -l app=myapp 。

kubectl describe <resource-type> <resource-name>：查看指定资源的详细描述，包含资源状态、事件等 。

kubectl describe <resource-type> <resource-name> -n <namespace>：查看指定命名空间下资源的详细信息 。
```


### （二）资源创建

```
kubectl create -f <yaml-file>：根据 YAML 文件创建资源 。

kubectl apply -f <yaml-file>：应用 YAML 文件创建或更新资源，推荐使用，可避免重复创建错误 。

kubectl create deployment <deployment-name> --image=<container-image>：创建一个 Deployment 资源 。

kubectl create service clusterip <service-name> --tcp=80:8080：创建一个 ClusterIP 类型的服务 。

kubectl create configmap <configmap-name> --from-literal=<key>=<value>：创建一个 ConfigMap，从字面量定义数据 。

kubectl create configmap <configmap-name> --from-file=<file-path>：从文件创建 ConfigMap 。

kubectl create secret generic <secret-name> --from-literal=<key>=<value>：创建一个普通类型的 Secret 。

kubectl create secret generic <secret-name> --from-file=<file-path>：从文件创建 Secret 。

kubectl create -f <yaml-file>：根据 YAML 文件创建资源 。

kubectl apply -f <yaml-file>：应用 YAML 文件创建或更新资源，推荐使用，可避免重复创建错误 。

kubectl create deployment <deployment-name> --image=<container-image>：创建一个 Deployment 资源 。

kubectl create service clusterip <service-name> --tcp=80:8080：创建一个 ClusterIP 类型的服务 。

kubectl create configmap <configmap-name> --from-literal=<key>=<value>：创建一个 ConfigMap，从字面量定义数据 。

kubectl create configmap <configmap-name> --from-file=<file-path>：从文件创建 ConfigMap 。

kubectl create secret generic <secret-name> --from-literal=<key>=<value>：创建一个普通类型的 Secret 。

kubectl create secret generic <secret-name> --from-file=<file-path>：从文件创建 Secret 。
```

### （三）资源更新

```

kubectl set image deployment/<deployment-name> <container-name>=<new-container-image>：更新 Deployment 中容器的镜像 。

kubectl scale deployment/<deployment-name> --replicas=<number>：调整 Deployment 的副本数量 。

kubectl patch <resource-type> <resource-name> -p '{"spec": {"<field>": "<new-value>"}}'：部分更新资源的字段 。

kubectl edit <resource-type> <resource-name>：直接编辑资源的配置 。
```


### （四）资源删除

```

kubectl delete <resource-type> <resource-name>：删除指定资源 。

kubectl delete -f <yaml-file>：删除 YAML 文件中定义的资源 。

kubectl delete <resource-type> -l <label>：通过标签删除资源 。

kubectl delete pods --all -n <namespace>：删除指定命名空间下的所有 Pod 。

kubectl delete service --all -n <namespace>：删除指定命名空间下的所有服务 。
```

## 四、Pod 管理命令

```

kubectl get pods：列出所有 Pod 。

kubectl get pods -n <namespace>：列出指定命名空间下的 Pod 。

kubectl describe pod <pod-name>：查看 Pod 的详细信息，如容器状态、事件等 。

kubectl describe pod <pod-name> -n <namespace>：查看指定命名空间下 Pod 的详细信息 。

kubectl logs <pod-name>：查看 Pod 中容器的日志 。

kubectl logs <pod-name> -n <namespace>：查看指定命名空间下 Pod 的日志 。

kubectl logs -f <pod-name>：实时跟踪 Pod 的日志输出 。

kubectl logs -f <pod-name> -n <namespace>：实时跟踪指定命名空间下 Pod 的日志 。

kubectl exec -it <pod-name> -- <command>：在 Pod 内执行命令，如kubectl exec -it my-pod -- sh进入 Pod 的 Shell 。

kubectl exec -it <pod-name> -n <namespace> -- <command>：在指定命名空间下的 Pod 内执行命令 。

kubectl port-forward <pod-name> <local-port>:<container-port>：将本地端口转发到 Pod 内的容器端口 。

kubectl port-forward <pod-name> <local-port>:<container-port> -n <namespace>：在指定命名空间下进行端口转发 。

kubectl delete pod <pod-name>：删除指定 Pod 。

kubectl delete pod <pod-name> -n <namespace>：删除指定命名空间下的 Pod 。

kubectl wait --for=condition=Ready pod/<pod-name>：等待 Pod 达到 Ready 状态 。

kubectl wait --for=condition=Ready pod/<pod-name> -n <namespace>：在指定命名空间下等待 Pod 达到 Ready 状态 。
```

## 五、Deployment 管理命令

```

kubectl get deployments：列出所有 Deployment 。

kubectl get deployments -n <namespace>：列出指定命名空间下的 Deployment 。

kubectl describe deployment <deployment-name>：查看 Deployment 的详细信息 。

kubectl describe deployment <deployment-name> -n <namespace>：查看指定命名空间下 Deployment 的详细信息 。

kubectl rollout status deployment/<deployment-name>：查看 Deployment 的滚动更新状态 。

kubectl rollout status deployment/<deployment-name> -n <namespace>：查看指定命名空间下 Deployment 的滚动更新状态 。

kubectl rollout history deployment/<deployment-name>：查看 Deployment 的更新历史记录 。

kubectl rollout history deployment/<deployment-name> -n <namespace>：查看指定命名空间下 Deployment 的更新历史 。

kubectl rollout undo deployment/<deployment-name>：回滚 Deployment 到上一个版本 。

kubectl rollout undo deployment/<deployment-name> --to-revision=<revision-number>：回滚到指定版本 。

kubectl scale deployment/<deployment-name> --replicas=<number>：调整 Deployment 的副本数量 。

kubectl set image deployment/<deployment-name> <container-name>=<new-container-image>：更新 Deployment 中容器的镜像 。

kubectl edit deployment <deployment-name>：编辑 Deployment 的配置 。

kubectl delete deployment <deployment-name>：删除 Deployment 。

kubectl delete deployment <deployment-name> -n <namespace>：删除指定命名空间下的 Deployment 。
```

## 六、Service 管理命令

```

kubectl get services：列出所有服务 。

kubectl get services -n <namespace>：列出指定命名空间下的服务 。

kubectl describe service <service-name>：查看服务的详细信息 。

kubectl describe service <service-name> -n <namespace>：查看指定命名空间下服务的详细信息 。

kubectl expose deployment <deployment-name> --type=ClusterIP --port=80 --target-port=8080：将 Deployment 暴露为 ClusterIP 类型的服务 。

kubectl expose deployment <deployment-name> --type=NodePort --port=80 --target-port=8080：将 Deployment 暴露为 NodePort 类型的服务 。

kubectl expose deployment <deployment-name> --type=LoadBalancer --port=80 --target-port=8080：将 Deployment 暴露为 LoadBalancer 类型的服务 。

kubectl create service clusterip <service-name> --tcp=80:8080：创建一个 ClusterIP 类型的服务 。

kubectl create service nodeport <service-name> --tcp=80:8080：创建一个 NodePort 类型的服务 。

kubectl create service loadbalancer <service-name> --tcp=80:8080：创建一个 LoadBalancer 类型的服务 。

kubectl edit service <service-name>：编辑服务的配置 。

kubectl delete service <service-name>：删除服务 。

kubectl delete service <service-name> -n <namespace>：删除指定命名空间下的服务。
```


## 七、ConfigMap 与 Secret 管理命令

### （一）ConfigMap

```

kubectl get configmaps：列出所有 ConfigMap 。

kubectl get configmaps -n <namespace>：列出指定命名空间下的 ConfigMap 。

kubectl describe configmap <configmap-name>：查看 ConfigMap 的详细信息 。

kubectl describe configmap <configmap-name> -n <namespace>：查看指定命名空间下 ConfigMap 的详细信息 。

kubectl create configmap <configmap-name> --from-literal=<key>=<value>：从字面量创建 ConfigMap 。

kubectl create configmap <configmap-name> --from-file=<file-path>：从文件创建 ConfigMap 。

kubectl delete configmap <configmap-name>：删除 ConfigMap 。

kubectl delete configmap <configmap-name> -n <namespace>：删除指定命名空间下的 ConfigMap 。

```


### （二）Secret

```

kubectl get secrets：列出所有 Secret 。

kubectl get secrets -n <namespace>：列出指定命名空间下的 Secret 。

kubectl describe secret <secret-name>：查看 Secret 的详细信息 。

kubectl describe secret <secret-name> -n <namespace>：查看指定命名空间下 Secret 的详细信息 。

kubectl create secret generic <secret-name> --from-literal=<key>=<value>：创建一个普通类型的 Secret 。

kubectl create secret generic <secret-name> --from-file=<file-path>：从文件创建 Secret 。

kubectl delete secret <secret-name>：删除 Secret 。

kubectl delete secret <secret-name> -n <namespace>：删除指定命名空间下的 Secret 。

```


## 八、其他命令


```

kubectl auth can-i <verb> <resource>：检查当前用户对指定资源是否有指定操作的权限 。

kubectl completion <shell>：生成命令行自动补全脚本，支持 bash、zsh 等 。


```


kubeadm

### namespace

查看命名空间
`kubectl get namespace`
或者简写成
`kubectl get ns`

创建命名空间
`kubectl create namespace [your-namespace]`

删除
`kubectl delete namespace [your-namespace]`
### pod

创建pod
`kubectl run mynginx --image=nginx:1.14.2`
可以指定命名空间
`kubectl run mynginx --image=nginx:1.14.2 -n my-namespace`

查看pod的详情
`kubectl describe pod <pod-name>`
最主要看里面的Events，多数操作可以从这里看到

pod创建成功后可以查看详细信息（-owide）
`kubectl get pod -owide -n <namespace-name>`
-w : 可以用来监听

查看
curl IP

删除pod
`kubectl delete pod <pod-name>`

进入pod，类似docker
`kubectl exec -it <my-pod> -c <tomcat> -- sh`


### deployment






### service

查看资源清单：
`kubectl edit svc tomcat`
或者
`kubectl get svc tomcat -oyaml`

### volume

### ConfigMap

### secret


### Ingress


### Helm



### 其他参考
[Kubernetes(K8s) —— 容器编排管理技术-腾讯云开发者社区-腾讯云](https://cloud.tencent.com/developer/article/1860439)