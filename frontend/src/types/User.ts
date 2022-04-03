export class User {
    constructor(
        readonly id: number,
        readonly firstName: string,
        readonly lastName: string,
        readonly email: string,
        readonly phone: string
    ) {}
}

export class Teacher extends User {}
export class TeachingAssistant extends User {}
export class Student extends User {}
