import { QueueType } from "@/types/QueueType";


export const getQueueTypeAsText = (queueType: QueueType) => {
    switch (queueType) {
    case QueueType.Submission:
        return "Godkjenning"
    case QueueType.Help:
        return "Hjelp"
    }
} 
